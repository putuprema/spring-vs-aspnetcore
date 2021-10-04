package serviceError

import (
	"fmt"
)

const (
	Unknown          = "9999"
	ResourceNotFound = "4040"
	EntityConflict   = "4041"
	BadCredentials   = "4010"
	TokenInvalid     = "4011"
	TokenExpired     = "4012"
	Forbidden        = "4030"
)

type ServiceError struct {
	Code    string
	Message string
}

func (s *ServiceError) Error() string {
	return fmt.Sprintf("%s (%s)", s.Message, s.Code)
}

func NewGenericError(code string, message string) *ServiceError {
	return &ServiceError{
		Code:    code,
		Message: message,
	}
}

func NewEntityNotFound(message string) *ServiceError {
	return &ServiceError{
		Code:    ResourceNotFound,
		Message: message,
	}
}

func NewEntityConflict(message string) *ServiceError {
	return &ServiceError{
		Code:    EntityConflict,
		Message: message,
	}
}

func NewForbidden(message string) *ServiceError {
	return &ServiceError{
		Code:    Forbidden,
		Message: message,
	}
}

func NewTokenInvalid() *ServiceError {
	return &ServiceError{
		Code:    TokenInvalid,
		Message: "Token invalid",
	}
}

func NewTokenExpired() *ServiceError {
	return &ServiceError{
		Code:    TokenExpired,
		Message: "Token expired",
	}
}
