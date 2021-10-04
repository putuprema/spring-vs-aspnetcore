using Eater.Dtos;
using Eater.Dtos.Query;
using Eater.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Eater.Controllers
{
    [Route("v1/orders")]
    [ApiController]
    [Authorize]
    public class ProductOrderController : ControllerBase
    {
        private readonly IProductOrderService _productOrderService;

        public ProductOrderController(IProductOrderService productOrderService)
        {
            _productOrderService = productOrderService;
        }

        [HttpGet]
        public async Task<List<ProductOrderDto>> GetOrders([FromQuery] ProductOrderQuery query)
        {
            List<ProductOrderDto> orders;

            if (!string.IsNullOrEmpty(query.ProductId))
            {
                orders = await _productOrderService.GetAllByProductId(query.ProductId);
            }
            else if (!string.IsNullOrEmpty(query.CustomerId))
            {
                orders = await _productOrderService.GetAllByCustomerId(query.CustomerId);
            }
            else
            {
                orders = await _productOrderService.GetAll();
            }

            return orders;
        }

        [HttpGet("{id}")]
        public async Task<ProductOrderDto> GetOrderById(string id) => await _productOrderService.GetById(id);
    }
}
