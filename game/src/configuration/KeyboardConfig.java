package configuration;

import com.badlogic.gdx.Input;
import configuration.values.ConfigIntValue;

@ConfigMap(path = {"keyboard"})
public class KeyboardConfig {

    public static final ConfigKey<Integer> MOVEMENT_UP =
            new ConfigKey<>(new String[] {"movement", "up"}, new ConfigIntValue(Input.Keys.W));
    public static final ConfigKey<Integer> MOVEMENT_DOWN =
            new ConfigKey<>(new String[] {"movement", "down"}, new ConfigIntValue(Input.Keys.S));
    public static final ConfigKey<Integer> MOVEMENT_LEFT =
            new ConfigKey<>(new String[] {"movement", "left"}, new ConfigIntValue(Input.Keys.A));
    public static final ConfigKey<Integer> MOVEMENT_RIGHT =
            new ConfigKey<>(new String[] {"movement", "right"}, new ConfigIntValue(Input.Keys.D));
    public static final ConfigKey<Integer> INVENTORY_OPEN =
            new ConfigKey<>(new String[] {"inventory", "open"}, new ConfigIntValue(Input.Keys.I));
    public static final ConfigKey<Integer> INTERACT_WORLD =
            new ConfigKey<>(new String[] {"interact", "world"}, new ConfigIntValue(Input.Keys.E));
    public static final ConfigKey<Integer> FIRST_SKILL =
            new ConfigKey<>(new String[] {"skill", "first"}, new ConfigIntValue(Input.Keys.Q));
    public static final ConfigKey<Integer> SECOND_SKILL =
            new ConfigKey<>(new String[] {"skill", "second"}, new ConfigIntValue(Input.Keys.R));
    public static final ConfigKey<Integer> THIRD_SKILL =
            new ConfigKey<>(new String[] {"skill", "third"}, new ConfigIntValue(Input.Keys.T));
    public static final ConfigKey<Integer> FOURTH_SKILL =
            new ConfigKey<>(new String[] {"sskill", "ffourth"}, new ConfigIntValue(Input.Keys.Z));
    public static final ConfigKey<Integer> USE_MAGICBOOK =
            new ConfigKey<>(new String[] {"Book", "Use"}, new ConfigIntValue(Input.Keys.H));
    public static final ConfigKey<Integer> USE_FLEISCH =
            new ConfigKey<>(new String[] {"Fleisch", "Use"}, new ConfigIntValue(Input.Keys.J));
    public static final ConfigKey<Integer> USE_POTION =
            new ConfigKey<>(new String[] {"Potion", "Use"}, new ConfigIntValue(Input.Keys.K));
    public static final ConfigKey<Integer> ADD_TO_BACk =
            new ConfigKey<>(new String[] {"Back", "ADD"}, new ConfigIntValue(Input.Keys.L));
}
