namespace Eater.Interfaces
{
    public interface IPasswordEncoder
    {
        public string Encode(string rawPassword);
        public bool Matches(string rawPassword, string encodedPassword);
    }
}
