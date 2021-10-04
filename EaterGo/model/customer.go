package model

type Customer struct {
	ID			string	`gorm:"primarykey"`
	Name		string
	Password	string
}
