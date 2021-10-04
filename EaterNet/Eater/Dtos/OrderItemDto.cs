namespace Eater.Dtos
{
    public class OrderItemDto
    {
        public string Id { get; set; }
        public string ProductId { get; set; }
        public string Name { get; set; }
        public long Price { get; set; }
        public int Qty { get; set; }

        public OrderItemDto(string id, string productId, string name, long price, int qty)
        {
            Id = id;
            ProductId = productId;
            Name = name;
            Price = price;
            Qty = qty;
        }
    }
}
