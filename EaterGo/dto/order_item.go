package dto

import "EaterGo/model"

type OrderItem struct {
	ID			string	`json:"id"`
	ProductID	string	`json:"productId"`
	Name		string	`json:"name"`
	Price		int64	`json:"price"`
	Qty			int32	`json:"qty"`
}

func NewOrderItem(model *model.OrderItem) OrderItem  {
	return OrderItem{
		ID: model.ID,
		ProductID: model.ProductID,
		Name: model.Name,
		Price: model.Price,
		Qty: model.Qty,
	}
}

func NewOrderItems(models []model.OrderItem) []OrderItem  {
	items := make([]OrderItem, 0)
	for _, i := range models {
		items = append(items, NewOrderItem(&i))
	}
	return items
}