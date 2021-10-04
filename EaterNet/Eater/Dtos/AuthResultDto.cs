namespace Eater.Dtos
{
    public class AuthResultDto
    {
        public string AccessToken { get; set; }
        public CustomerDto Profile { get; set; }

        public AuthResultDto(string accessToken, CustomerDto profile)
        {
            AccessToken = accessToken;
            Profile = profile;
        }

    }
}
