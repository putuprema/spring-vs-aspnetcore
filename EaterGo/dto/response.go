package dto

type BaseResponse struct {
	Message string      `json:"message"`
}

func NewBaseResponse(message string) *BaseResponse {
	return &BaseResponse{
		Message: message,
	}
}