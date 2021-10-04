package controller

import (
	"EaterGo/service"
	"EaterGo/utils"
	"github.com/gin-gonic/gin"
	"net/http"
)

type ProductOrderController struct {
	productOrderService service.IProductOrderService
}

func (c ProductOrderController) GetOrderById() gin.HandlerFunc  {
	return func(ctx *gin.Context) {
		orderId := ctx.Param("id")
		productOrders, err := c.productOrderService.GetById(orderId)
		if err != nil {
			utils.HandleRequestError(ctx, err)
			return
		}
		ctx.JSON(http.StatusOK, productOrders)
	}
}

func NewProductOrderController(productOrderService service.IProductOrderService) *ProductOrderController  {
	return &ProductOrderController{
		productOrderService: productOrderService,
	}
}