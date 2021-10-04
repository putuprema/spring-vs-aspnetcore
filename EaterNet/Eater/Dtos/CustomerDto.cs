namespace Eater.Dtos
{
    public class CustomerDto
    {
        public string Id { get; set; }
        public string Name { get; set; }

        public CustomerDto(string id, string name)
        {
            Id = id;
            Name = name;
        }
    }
}
