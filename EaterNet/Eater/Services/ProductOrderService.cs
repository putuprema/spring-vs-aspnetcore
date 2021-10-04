using Eater.Dtos;
using Eater.Exceptions;
using Eater.Interfaces;
using Mapster;

namespace Eater.Services
{
    public class ProductOrderService : IProductOrderService
    {
        private readonly IProductOrderRepository _productOrderRepository;
        private readonly ICustomerRepository _customerRepository;

        public ProductOrderService(IProductOrderRepository productOrderRepository, ICustomerRepository customerRepository)
        {
            _productOrderRepository = productOrderRepository;
            _customerRepository = customerRepository;
        }

        public async Task<List<ProductOrderDto>> GetAll()
        {
            var orders = await _productOrderRepository.GetAll();
            return orders.Adapt<List<ProductOrderDto>>();
        }

        public async Task<List<ProductOrderDto>> GetAllByCustomerId(string customerId)
        {
            var customer = await _customerRepository.GetById(customerId);
            if (customer == null)
            {
                throw new NotFoundException("Customer not found");
            }

            var orders = await _productOrderRepository.GetAllByCustomer(customer);
            return orders.Adapt<List<ProductOrderDto>>();
        }

        public async Task<List<ProductOrderDto>> GetAllByProductId(string productId)
        {
            var orders = await _productOrderRepository.GetAllByProductId(productId);
            return orders.Adapt<List<ProductOrderDto>>();
        }

        public async ValueTask<ProductOrderDto> GetById(string id)
        {
            var productOrder = await _productOrderRepository.GetById(id);
            if (productOrder == null)
            {
                throw new NotFoundException("Order not found");
            }

            return productOrder.Adapt<ProductOrderDto>();
        }
    }
}
