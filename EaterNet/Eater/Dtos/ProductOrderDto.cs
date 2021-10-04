namespace Eater.Dtos
{
    public class ProductOrderDto
    {
        public string Id { get; set; }
        public DateTime CreatedAt { get; set; }
        public CustomerDto Customer { get; set; }
        public ISet<OrderItemDto> Items { get; set; }

        public ProductOrderDto(string id, DateTime createdAt, CustomerDto customer, ISet<OrderItemDto> items)
        {
            Id = id;
            CreatedAt = createdAt;
            Customer = customer;
            Items = items;
        }
    }
}
