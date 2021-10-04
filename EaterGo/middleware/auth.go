package middleware

import (
	"EaterGo/service"
	"github.com/gin-gonic/gin"
	"net/http"
	"strings"
)

type AuthMiddleware struct {
	tokenService service.ITokenService
}

func (m *AuthMiddleware) Authorize() gin.HandlerFunc {
	return func(ctx *gin.Context) {
		authHeader := ctx.GetHeader("Authorization")
		if !strings.HasPrefix(authHeader, "Bearer") {
			ctx.AbortWithStatus(http.StatusUnauthorized)
			return
		}

		tokenStr := strings.Replace(authHeader, "Bearer ", "", 1)

		claims, err := m.tokenService.Parse(tokenStr)
		if err != nil {
			ctx.AbortWithStatus(http.StatusUnauthorized)
			return
		}

		ctx.Set("userId", &claims.Id)
		ctx.Next()
	}
}

func NewAuthMiddleware(tokenService service.ITokenService) *AuthMiddleware {
	return &AuthMiddleware{
		tokenService: tokenService,
	}
}