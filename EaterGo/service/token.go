package service

import (
	"EaterGo/model"
	serviceError "EaterGo/service-error"
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"time"
)

type jwtClaims struct {
	jwt.StandardClaims
}

type ITokenService interface {
	GenerateToken(customer *model.Customer) (string, error)
	Parse(token string) (*jwtClaims, error)
}

type TokenService struct {
	secretKey string
}

func (t *TokenService) GenerateToken(customer *model.Customer) (string, error) {
	claims := &jwtClaims{
		StandardClaims: jwt.StandardClaims{
			ExpiresAt: time.Now().Add(time.Hour * 24).Unix(),
			Id:        fmt.Sprintf("%s", customer.ID),
			Issuer:    "Eater",
		},
	}

	token := jwt.NewWithClaims(jwt.SigningMethodHS256, claims)
	return token.SignedString([]byte(t.secretKey))
}

func (t *TokenService) Parse(token string) (*jwtClaims, error) {
	parsedToken, err := jwt.ParseWithClaims(token, &jwtClaims{}, func(token *jwt.Token) (interface{}, error) {
		if _, valid := token.Method.(*jwt.SigningMethodHMAC); !valid {
			return nil, serviceError.NewTokenInvalid()
		}
		return []byte(t.secretKey), nil
	})
	if err != nil {
		if err.(*jwt.ValidationError).Errors == jwt.ValidationErrorExpired {
			return nil, serviceError.NewTokenExpired()
		}
		return nil, serviceError.NewTokenInvalid()
	}

	if claims, ok := parsedToken.Claims.(*jwtClaims); ok && parsedToken.Valid {
		return claims, nil
	} else {
		return nil, serviceError.NewTokenInvalid()
	}
}

func NewTokenService(secretKey string) ITokenService {
	return &TokenService{
		secretKey: secretKey,
	}
}