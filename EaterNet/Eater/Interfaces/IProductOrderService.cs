using Eater.Dtos;

namespace Eater.Interfaces
{
    public interface IProductOrderService
    {
        public ValueTask<ProductOrderDto> GetById(string id);
        public Task<List<ProductOrderDto>> GetAll();
        public Task<List<ProductOrderDto>> GetAllByCustomerId(string customerId);
        public Task<List<ProductOrderDto>> GetAllByProductId(string productId);
    }
}
