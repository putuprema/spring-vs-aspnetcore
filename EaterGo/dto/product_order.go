package dto

import (
	"EaterGo/model"
	"time"
)

type ProductOrder struct {
	ID			string			`json:"id"`
	CreatedAt	time.Time		`json:"createdAt"`
	Customer	Customer		`json:"customer"`
	Items		[]OrderItem		`json:"items"`
}

func NewProductOrder(model *model.ProductOrder) ProductOrder {
	return ProductOrder{
		ID: model.ID,
		CreatedAt: model.CreatedAt,
		Customer: NewCustomer(&model.Customer),
		Items: NewOrderItems(model.Items),
	}
}

func NewProductOrders(models []model.ProductOrder) []ProductOrder  {
	orders := make([]ProductOrder, 0)
	for _, o := range models {
		orders = append(orders, NewProductOrder(&o))
	}
	return orders
}