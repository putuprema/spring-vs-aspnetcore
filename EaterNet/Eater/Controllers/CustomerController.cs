using Eater.Dtos;
using Eater.Dtos.Form;
using Eater.Interfaces;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace Eater.Controllers
{
    [Route("v1/customer")]
    [ApiController]
    [Authorize]
    public class CustomerController : ControllerBase
    {
        private readonly ICustomerService _customerService;

        public CustomerController(ICustomerService customerService)
        {
            _customerService = customerService;
        }

        [HttpPost("register")]
        [AllowAnonymous]
        public async Task<AuthResultDto> Register([FromBody] RegisterForm form) => await _customerService.Register(form);

        [HttpPost("login")]
        [AllowAnonymous]
        public async Task<AuthResultDto> Login([FromBody] LoginForm form) => await _customerService.Login(form);

        [HttpGet]
        public async Task<CustomerDto> GetProfile() => await _customerService.GetProfile();
    }
}
