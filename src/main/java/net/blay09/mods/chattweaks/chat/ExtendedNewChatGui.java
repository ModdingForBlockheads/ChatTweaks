package net.blay09.mods.chattweaks.chat;

import net.blay09.mods.chattweaks.api.ChatMessage;
import net.blay09.mods.chattweaks.api.ClearChatEvent;
import net.blay09.mods.chattweaks.api.event.PrintChatMessageEvent;
import net.blay09.mods.chattweaks.api.ChatDisplay;
import net.blay09.mods.chattweaks.api.ChatView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.MinecraftForge;

public class ExtendedNewChatGui extends NewChatGui implements ChatDisplay {

    protected final Minecraft mc;

    public ExtendedNewChatGui(Minecraft minecraft) {
        super(minecraft);
        this.mc = minecraft;
    }

    @Override
    public void printChatMessageWithOptionalDeletion(ITextComponent chatComponent, int chatLineId) {
        PrintChatMessageEvent event = new PrintChatMessageEvent(chatComponent, chatLineId);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            super.printChatMessageWithOptionalDeletion(event.getChatComponent(), event.getChatLineId());
        }
    }

    @Override
    public void clearChatMessages(boolean clearSentMsgHistory) {
        ClearChatEvent event = new ClearChatEvent(clearSentMsgHistory);
        if (!MinecraftForge.EVENT_BUS.post(event)) {
            super.clearChatMessages(event.isClearSentMessageHistory());
        }
    }

    @Override
    public String getName() {
        return "chat";
    }

    @Override
    public void addChatMessage(ChatMessage chatMessage, ChatView view) {
        this.func_238493_a_(chatMessage.getTextComponent(), chatMessage.getChatLineId(), mc.ingameGUI.getTicks(), false);
        /*if (view != ChatViewManager.getActiveView()) {
            return;
        }

        int chatWidth = MathHelper.floor((float) this.getChatWidth() / this.getChatScale());
        List<ITextComponent> wrappedList = GuiUtilRenderComponents.splitText(chatMessage.getTextComponent(), chatWidth, this.mc.fontRenderer, false, false);
        boolean isChatOpen = this.getChatOpen();
        int colorIndex = -1;
        int emoteIndex = 0;
        for (ITextComponent chatLine : wrappedList) {
            if (isChatOpen && this.scrollPos > 0) {
                this.isScrolled = true;
                this.scroll(1);
            }

            String formattedText = chatLine.getFormattedText();
            if (ChatTweaksConfig.CLIENT.disableUnderlines.get()) {
                formattedText = UNDERLINE_CODE_PATTERN.matcher(formattedText).replaceAll("");
            }

            Matcher splitMatcher = CUSTOM_FORMATTING_CODE_PATTERN.matcher(formattedText);
            List<TextRenderRegion> regions = Lists.newArrayList();
            int lastIdx = 0;
            while (splitMatcher.find()) {
                String code = splitMatcher.group(1);
                regions.add(new TextRenderRegion(formattedText.substring(lastIdx, splitMatcher.start()), chatMessage.getRGBColor(colorIndex)));
                if (code.equals("#")) {
                    colorIndex++;
                }

                lastIdx = splitMatcher.end();
            }

            if (lastIdx < formattedText.length()) {
                regions.add(new TextRenderRegion(formattedText.substring(lastIdx), chatMessage.getRGBColor(colorIndex)));
            }

            String cleanText = FORMATTING_CODE_PATTERN.matcher(chatLine.getUnformattedText()).replaceAll("");
            Matcher matcher = EMOTE_PATTERN.matcher(cleanText);
            List<ChatImage> images = null;
            if (chatMessage.hasImages()) {
                images = Lists.newArrayList();
                while (matcher.find()) {
                    ChatImage image = chatMessage.getImage(emoteIndex);
                    if (image != null) {
                        image.setIndex(matcher.start());
                        images.add(image);
                    }
                    emoteIndex++;
                }
            }

            this.wrappedChatLines.add(0, new WrappedChatLine(mc.ingameGUI.getUpdateCounter(), chatMessage, chatLine, cleanText, regions, images, alternateBackground));
        }

        while (this.wrappedChatLines.size() > ChatTweaks.MAX_MESSAGES) {
            this.wrappedChatLines.remove(this.wrappedChatLines.size() - 1);
        }

        alternateBackground = !alternateBackground;*/
    }
}