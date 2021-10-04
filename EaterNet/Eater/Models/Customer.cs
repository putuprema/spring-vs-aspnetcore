namespace Eater.Models
{
    public class Customer
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Password { get; set; }
        public List<ProductOrder> ProductOrders { get; set; } = new();

        public Customer(string id, string name, string password)
        {
            Id = id;
            Name = name;
            Password = password;
        }
    }
}
