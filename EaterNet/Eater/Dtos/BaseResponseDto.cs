namespace Eater.Dtos
{
    public class BaseResponseDto
    {
        public string Message { get; set; }

        public BaseResponseDto(string message)
        {
            Message = message;
        }
    }
}
