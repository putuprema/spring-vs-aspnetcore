package dto

import "EaterGo/model"

type Customer struct {
	ID		string	`json:"id"`
	Name	string	`json:"name"`
}

func NewCustomer(model *model.Customer) Customer {
	return Customer{
		ID: model.ID,
		Name: model.Name,
	}
}