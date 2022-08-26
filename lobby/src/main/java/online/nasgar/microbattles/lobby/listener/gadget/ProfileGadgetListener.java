package online.nasgar.microbattles.lobby.listener.gadget;

import com.cryptomorin.xseries.XSound;
import online.nasgar.microbattles.api.adapt.NbtHandler;
import online.nasgar.microbattles.api.sound.SoundPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.inject.Inject;

public class ProfileGadgetListener implements Listener {
  private final NbtHandler nbtHandler;

  @Inject
  public ProfileGadgetListener(NbtHandler nbtHandler) {
    this.nbtHandler = nbtHandler;
  }

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    ItemStack itemStack = event.getItem();

    if (itemStack == null) {
      return;
    }

    String clickable = nbtHandler.getTag(
      itemStack,
      "microbattles:clickable"
    );

    if (clickable == null || !clickable.equals("profile")) {
      return;
    }

    SoundPlayer.playNormal(player, XSound.UI_BUTTON_CLICK);
    player.performCommand("profile");
  }
}
