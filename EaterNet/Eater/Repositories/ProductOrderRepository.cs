using Eater.Interfaces;
using Eater.Models;
using Microsoft.EntityFrameworkCore;

namespace Eater.Repositories
{
    public class ProductOrderRepository : BaseRepository<ProductOrder>, IProductOrderRepository
    {
        public ProductOrderRepository(AppDbContext context) : base(context)
        {
        }

        public Task<ProductOrder?> GetById(string id) => _context.ProductOrder
            .Include(o => o.Customer)
            .Include(o => o.Items)
            .Where(o => o.Id == id)
            .FirstOrDefaultAsync();

        public new Task<List<ProductOrder>> GetAll() => _context.ProductOrder
            .Include(o => o.Customer)
            .Include(o => o.Items)
            .OrderByDescending(o => o.CreatedAt)
            .ToListAsync();

        public Task<List<ProductOrder>> GetAllByCustomer(Customer customer) => _context.ProductOrder
            .Include(o => o.Items)
            .Where(o => o.Customer == customer)
            .ToListAsync();

        public Task<List<ProductOrder>> GetAllByProductId(string productId) => _context.ProductOrder
            .Include(o => o.Customer)
            .Include(o => o.Items)
            .Where(o => o.Items.Any(item => item.ProductId == productId))
            .ToListAsync();
    }
}
