using System.ComponentModel.DataAnnotations;

namespace Eater.Dtos.Form
{
    public class RegisterForm
    {
        [Required(ErrorMessage = "Please enter user id")]
        public string UserId { get; set; }

        [Required(ErrorMessage = "Please enter name")]
        public string Name { get; set; }

        [Required(ErrorMessage = "Please enter password")]
        [MinLength(6, ErrorMessage = "Password must have minimum of 6 characters")]
        public string Password { get; set; }

        public RegisterForm(string userId, string name, string password)
        {
            UserId = userId;
            Name = name;
            Password = password;
        }
    }
}
