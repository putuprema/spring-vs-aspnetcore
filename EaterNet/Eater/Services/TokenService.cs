using Eater.Config;
using Eater.Interfaces;
using Eater.Models;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace Eater.Services
{
    public class TokenService : ITokenService
    {
        private readonly JwtConfig _jwtConfig;
        private readonly SymmetricSecurityKey _signingKey;

        public TokenService(IOptions<JwtConfig> jwtOpts)
        {
            _jwtConfig = jwtOpts.Value;
            _signingKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_jwtConfig.Secret));
        }

        public string GenerateToken(Customer customer)
        {
            var authClaims = new List<Claim>
            {
                new Claim(Constants.ClaimTypes.Id, customer.Id),
                new Claim(Constants.ClaimTypes.Subject, customer.Name)
            };

            var token = new JwtSecurityToken(
                issuer: _jwtConfig.ValidIssuer,
                expires: DateTime.Now.AddSeconds(_jwtConfig.Lifetime),
                claims: authClaims,
                signingCredentials: new SigningCredentials(_signingKey, SecurityAlgorithms.HmacSha256)
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}
