using Eater.Dtos;
using Microsoft.AspNetCore.Mvc;

namespace Eater.Exceptions
{
    public class UnauthorizedException : AppException
    {
        public UnauthorizedException(string message) : base(message)
        {
        }

        public override IActionResult GetResponse()
        {
            return new UnauthorizedObjectResult(new BaseResponseDto(Message));
        }
    }
}
