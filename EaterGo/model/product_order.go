package model

import "time"

type ProductOrder struct {
	ID			string	`gorm:"primarykey"`
	CreatedAt	time.Time
	CustomerID	string
	Customer	Customer
	Items		[]OrderItem
}
