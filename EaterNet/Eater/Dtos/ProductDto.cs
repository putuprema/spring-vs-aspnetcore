namespace Eater.Dtos
{
    public class ProductDto
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public long Price { get; set; }

        public ProductDto(string id, string name, long price)
        {
            Id = id;
            Name = name;
            Price = price;
        }
    }
}
