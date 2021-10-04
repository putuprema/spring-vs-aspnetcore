using Eater.Dtos;
using Eater.Dtos.Form;

namespace Eater.Interfaces
{
    public interface ICustomerService
    {
        public Task<AuthResultDto> Register(RegisterForm form);
        public Task<AuthResultDto> Login(LoginForm form);
        public Task<CustomerDto> GetProfile();
    }
}
