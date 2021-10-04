using Eater.Dtos;
using Microsoft.AspNetCore.Mvc;

namespace Eater.Exceptions
{
    public class NotFoundException : AppException
    {
        public NotFoundException(string message) : base(message)
        {
        }

        public override IActionResult GetResponse()
        {
            return new NotFoundObjectResult(new BaseResponseDto(Message));
        }
    }
}
