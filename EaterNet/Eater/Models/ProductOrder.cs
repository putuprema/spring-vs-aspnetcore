namespace Eater.Models
{
    public class ProductOrder
    {
        public string Id { get; set; } = Guid.NewGuid().ToString();
        public DateTime CreatedAt { get; set; } = DateTime.Now;
        public Customer? Customer { get; set; }
        public ISet<OrderItem> Items { get; set; } = new HashSet<OrderItem>();

        public void AddItem(Product product, int qty)
        {
            var item = new OrderItem(
                productId: product.Id,
                name: product.Name,
                price: product.Price,
                qty: qty);

            Items.Add(item);
        }
    }
}
