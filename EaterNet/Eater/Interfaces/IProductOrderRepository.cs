using Eater.Models;

namespace Eater.Interfaces
{
    public interface IProductOrderRepository : IBaseRepository<ProductOrder>
    {
        public Task<ProductOrder?> GetById(string id);
        public Task<List<ProductOrder>> GetAllByCustomer(Customer customer);
        public Task<List<ProductOrder>> GetAllByProductId(string productId);
    }
}
