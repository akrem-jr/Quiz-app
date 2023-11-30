import logging
from telegram import Update, InlineKeyboardButton, InlineKeyboardMarkup
from telegram.ext import (
    Updater,
    CommandHandler,
    CallbackQueryHandler,
    CallbackContext,
)


# Set up logging
logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
                    level=logging.INFO)
logger = logging.getLogger(__name__)

# Define the commands
COMMANDS = {
    "vip": "VIP Membership",
    "silver": "Silver Membership",
    "gold": "Gold Membership",
    "contact": "Contact Admin",
    "channel": "Join Layne Channel",
}

# Start command handler
def start(update: Update, context: CallbackContext) -> None:
    keyboard = []
    for cmd, desc in COMMANDS.items():
        keyboard.append([InlineKeyboardButton(desc, callback_data=cmd)])

    reply_markup = InlineKeyboardMarkup(keyboard)

    update.message.reply_text('Welcome to Layne Bot!', reply_markup=reply_markup)

# Callback query handler
def button(update: Update, context: CallbackContext) -> None:
    query = update.callback_query
    query.answer()

    command = query.data
    if command == 'vip':
        query.message.reply_text('You selected VIP Membership.')
    elif command == 'silver':
        query.message.reply_text('You selected Silver Membership.')
    elif command == 'gold':
        query.message.reply_text('You selected Gold Membership.')
    elif command == 'contact':
        query.message.reply_text('Please contact the admin.')
    elif command == 'channel':
        query.message.reply_text('Join Layne Channel at t.me/layne_channel.')

    query.message.reply_text('What else can I help you with?')

def main() -> None:
    # Create the Updater and pass in your bot's token
    updater = Updater(token='YOUR_BOT_TOKEN', use_context=True)

    # Get the dispatcher to register handlers
    dispatcher = updater.dispatcher

    # Add command handlers
    dispatcher.add_handler(CommandHandler('start', start))
    dispatcher.add_handler(CallbackQueryHandler(button))

    # Start the bot
    updater.start_polling()

    # Run the bot until you press Ctrl-C
    updater.idle()

if __name__ == '__main__':
    main()