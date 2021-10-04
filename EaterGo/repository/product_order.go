package repository

import (
	"EaterGo/model"
	"gorm.io/gorm"
)

type IProductOrderRepository interface {
	GetById(id string) (*model.ProductOrder, error)
	GetAll() ([]model.ProductOrder, error)
	GetAllByCustomer(customer *model.Customer) ([]model.ProductOrder, error)
	GetAllByProductId(productId string) ([]model.ProductOrder, error)
}

type ProductOrderRepository struct {
	db *gorm.DB
}

func (p *ProductOrderRepository) GetById(id string) (*model.ProductOrder, error) {
	var foundOrder model.ProductOrder
	if err := p.db.
		Joins("Customer").
		Preload("Items").
		First(&foundOrder, "product_order.id = ?", id).Error;
	err != nil {
		return nil, err
	}
	return &foundOrder, nil
}

func (p *ProductOrderRepository) GetAll() ([]model.ProductOrder, error) {
	orders := make([]model.ProductOrder, 0)
	if err := p.db.
		Preload("Customer").
		Preload("Items").
		Find(&orders).Error;
	err != nil {
		return nil, err
	}
	return orders, nil
}

func (p *ProductOrderRepository) GetAllByCustomer(customer *model.Customer) ([]model.ProductOrder, error) {
	orders := make([]model.ProductOrder, 0)
	if err := p.db.
		Preload("Customer").
		Preload("Items").
		Where("customer_id = ?", customer.ID).Error;
	err != nil {
		return nil, err
	}
	return orders, nil
}

func (p *ProductOrderRepository) GetAllByProductId(productId string) ([]model.ProductOrder, error) {
	panic("implement me")
}

func NewProductOrderRepository(db *gorm.DB) IProductOrderRepository {
	return &ProductOrderRepository{db: db}
}