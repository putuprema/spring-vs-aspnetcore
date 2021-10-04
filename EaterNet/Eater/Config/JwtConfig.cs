namespace Eater.Config
{
    public class JwtConfig
    {
        public string ValidIssuer { get; set; } = null!;
        public string Secret { get; set; } = null!;
        public int Lifetime { get; set; }
    }
}
