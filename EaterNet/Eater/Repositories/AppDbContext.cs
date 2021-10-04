using Eater.Models;
using Microsoft.EntityFrameworkCore;

namespace Eater.Repositories
{
    public class AppDbContext : DbContext
    {
        public DbSet<Customer> Customer { get; set; }
        public DbSet<Product> Product { get; set; }
        public DbSet<ProductOrder> ProductOrder { get; set; }
        public DbSet<OrderItem> OrderItem { get; set; }

        public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
        {
            Customer = Set<Customer>();
            Product = Set<Product>();
            ProductOrder = Set<ProductOrder>();
            OrderItem = Set<OrderItem>();
        }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSnakeCaseNamingConvention();
        }
    }
}
