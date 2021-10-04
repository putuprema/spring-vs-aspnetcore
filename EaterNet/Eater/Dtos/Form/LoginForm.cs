using System.ComponentModel.DataAnnotations;

namespace Eater.Dtos.Form
{
    public class LoginForm
    {
        [Required(ErrorMessage = "Please enter user id")]
        public string UserId { get; set; }

        [Required(ErrorMessage = "Please enter password")]
        [MinLength(6, ErrorMessage = "Password must have minimum of 6 characters")]
        public string Password { get; set; }

        public LoginForm(string userId, string password)
        {
            UserId = userId;
            Password = password;
        }
    }
}
