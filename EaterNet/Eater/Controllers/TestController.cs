using Microsoft.AspNetCore.Mvc;

namespace Eater.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TestController : ControllerBase
    {
        [HttpGet("[action]")]
        public string SayHello() => "Hello";
    }
}
