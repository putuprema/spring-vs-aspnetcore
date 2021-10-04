using Eater.Models;

namespace Eater.Interfaces
{
    public interface ITokenService
    {
        public string GenerateToken(Customer customer);
    }
}
