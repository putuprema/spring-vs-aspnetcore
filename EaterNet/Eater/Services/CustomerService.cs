using Eater.Dtos;
using Eater.Dtos.Form;
using Eater.Exceptions;
using Eater.Interfaces;
using Eater.Models;
using Mapster;
using System.Security.Claims;

namespace Eater.Services
{
    public class CustomerService : ICustomerService
    {
        private readonly IHttpContextAccessor _httpContextAccessor;
        private readonly ICustomerRepository _customerRepository;
        private readonly ITokenService _tokenService;
        private readonly IPasswordEncoder _passwordEncoder;

        public CustomerService(IHttpContextAccessor httpContextAccessor, ICustomerRepository customerRepository, ITokenService tokenService, IPasswordEncoder passwordEncoder)
        {
            _httpContextAccessor = httpContextAccessor;
            _customerRepository = customerRepository;
            _tokenService = tokenService;
            _passwordEncoder = passwordEncoder;
        }

        public async Task<CustomerDto> GetProfile()
        {
            var userId = _httpContextAccessor.HttpContext!.User!.FindFirstValue(Constants.ClaimTypes.Id);
            var customer = await _customerRepository.GetById(userId!);
            if (customer == null)
            {
                throw new NotFoundException("User not found");
            }
            return customer.Adapt<CustomerDto>();
        }

        public async Task<AuthResultDto> Login(LoginForm form)
        {
            var customer = await _customerRepository.GetById(form.UserId);
            if (customer == null)
            {
                throw new UnauthorizedException("Bad Credentials");
            }

            if (!_passwordEncoder.Matches(form.Password, customer.Password))
            {
                throw new UnauthorizedException("Bad Credentials");
            }

            return new AuthResultDto(
                accessToken: _tokenService.GenerateToken(customer),
                profile: customer.Adapt<CustomerDto>()
            );
        }

        public async Task<AuthResultDto> Register(RegisterForm form)
        {
            var existingCustomer = await _customerRepository.GetById(form.UserId);
            if (existingCustomer != null)
            {
                throw new ConflictException("User already exists");
            }

            var customer = new Customer(form.UserId, form.Name, _passwordEncoder.Encode(form.Password));
            _customerRepository.Save(customer);
            await _customerRepository.SaveChangesAsync();

            return new AuthResultDto(
                accessToken: _tokenService.GenerateToken(customer),
                profile: customer.Adapt<CustomerDto>()
            );
        }
    }
}
