namespace Eater.Models
{
    public class OrderItem
    {
        public string Id { get; set; } = Guid.NewGuid().ToString();
        //public ProductOrder? ProductOrder { get; set; }
        public string ProductId { get; set; }
        public string Name { get; set; }
        public long Price { get; set; }
        public int Qty { get; set; }

        public OrderItem(string productId, string name, long price, int qty)
        {
            ProductId = productId;
            Name = name;
            Price = price;
            Qty = qty;
        }
    }
}
