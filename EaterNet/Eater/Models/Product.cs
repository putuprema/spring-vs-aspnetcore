namespace Eater.Models
{
    public class Product
    {
        public string Id { get; set; } = Guid.NewGuid().ToString();
        public string Name { get; set; }
        public long Price { get; set; }

        public Product(string name, long price)
        {
            this.Name = name;
            this.Price = price;
        }
    }
}
