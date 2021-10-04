package service

import (
	"EaterGo/dto"
	"EaterGo/repository"
	serviceError "EaterGo/service-error"
	"github.com/pkg/errors"
	"gorm.io/gorm"
)

type IProductOrderService interface {
	GetById(id string) (*dto.ProductOrder, error)
	GetAll() ([]dto.ProductOrder, error)
}

type ProductOrderService struct {
	productOrderRepository repository.IProductOrderRepository
}

func (p *ProductOrderService) GetById(id string) (*dto.ProductOrder, error) {
	productOrder, err := p.productOrderRepository.GetById(id)
	if err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return nil, serviceError.NewEntityNotFound("Order not found")
		}
		return nil, err
	}
	productOrderDto := dto.NewProductOrder(productOrder)
	return &productOrderDto, nil
}

func (p *ProductOrderService) GetAll() ([]dto.ProductOrder, error) {
	productOrders, err := p.productOrderRepository.GetAll()
	if err != nil {
		return nil, err
	}
	return dto.NewProductOrders(productOrders), nil
}

func NewProductOrderService(productOrderRepository repository.IProductOrderRepository) IProductOrderService  {
	return &ProductOrderService{
		productOrderRepository: productOrderRepository,
	}
}