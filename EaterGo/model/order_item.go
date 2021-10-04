package model

type OrderItem struct {
	ID				string	`gorm:"primarykey"`
	ProductOrderID	string
	ProductID		string
	Name			string
	Price			int64
	Qty				int32
}
