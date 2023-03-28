using Microsoft.AspNetCore.SignalR;
using System.Threading.Tasks;

namespace APIthuexe.Hubs
{
    public class ChatHub : Hub
    {
        public async Task SendMessage(string user, string message)
        {
            await Clients.All.SendAsync("ReceiveMessage", user, message);
        }
        public async Task SendMessage1(string user, string message)
        {
            await Clients.All.SendAsync("ReceiveMessage1", user, message);
        }
    }
}
