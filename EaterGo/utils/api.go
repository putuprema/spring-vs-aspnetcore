package utils

import (
	"EaterGo/dto"
	serviceError "EaterGo/service-error"
	"github.com/gin-gonic/gin"
	"github.com/go-playground/validator/v10"
	"log"
	"net/http"
)

func HandleValidationError(ctx *gin.Context, errors validator.ValidationErrors) {
	errorMap := make(map[string]string)

	for _, err := range errors {
		errorMap[err.Field()] = err.Error()
	}

	ctx.JSON(http.StatusBadRequest, dto.NewBaseResponse("Bad Request"))
}

func HandleRequestError(ctx *gin.Context, err error) {
	log.Printf("Exception caught at [(%s) %s]: %v", ctx.Request.Method, ctx.FullPath(), err)

	switch err.(type) {
	case *serviceError.ServiceError:
		actualErr := err.(*serviceError.ServiceError)

		switch actualErr.Code {
		case serviceError.ResourceNotFound:
			ctx.JSON(http.StatusNotFound, dto.NewBaseResponse(actualErr.Message))
		case serviceError.Forbidden:
			ctx.JSON(http.StatusForbidden, dto.NewBaseResponse(actualErr.Message))
		case serviceError.TokenInvalid, serviceError.TokenExpired:
			ctx.JSON(http.StatusUnauthorized, dto.NewBaseResponse(actualErr.Message))
		case serviceError.Unknown:
			ctx.JSON(http.StatusInternalServerError, dto.NewBaseResponse(actualErr.Message))
		default:
			ctx.JSON(http.StatusConflict, dto.NewBaseResponse(actualErr.Message))
		}
	default:
		ctx.JSON(http.StatusInternalServerError, dto.NewBaseResponse("Unknown error occured, please try again later."))
	}
}

//func GetAuthenticationPrincipal(ctx *gin.Context) (*dto.User, error) {
//	userFromCtx, _ := ctx.Get("User")
//	userDto, ok := userFromCtx.(*dto.User)
//	if !ok {
//		return nil, serviceError.NewGenericError(fmt.Sprintf("%d", http.StatusUnauthorized), "Unauthorized")
//	}
//	return userDto, nil
//}
