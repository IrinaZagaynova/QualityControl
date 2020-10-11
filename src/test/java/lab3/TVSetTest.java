package lab3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

class TVSetTest {
    public TVSet tvSet;

    @BeforeEach
    public void setUp() {
        tvSet = new TVSet();
    }

    @Test
    @DisplayName("tv initial state is correct")
    public void shouldCorrectlyInitialState() {
        assertFalse(tvSet.isTurnedOn());
        assertEquals(tvSet.getChannel(), 0);
    }

    @Nested
    @DisplayName("when tv is turn off")
    class TvIsTurnOff {
        @Test
        @DisplayName("can't select channel")
        public void shouldNotSelectChannel() {
            assertFalse(tvSet.selectChannel(10));
        }

        @Test
        @DisplayName("can't select previous channel")
        public void shouldNotSelectPreviousChannel() {
            assertFalse(tvSet.selectPreviousChannel());
        }

        @Test
        @DisplayName("can't set channel name")
        public void shouldNotSetChannelName() {
            assertFalse(tvSet.setChannelName(10, "10 channel"));
        }
    }

    @Test
    @DisplayName("can be turned on")
    public void shouldTurnedOn() {
        tvSet.turnOn();
        assertTrue(tvSet.isTurnedOn());
    }

    @Nested
    @DisplayName("when tv is turn on")
    class TvIsTurnOn {
        @BeforeEach
        public void init() {
            tvSet.turnOn();
        }

        @Test
        @DisplayName("should display 1 channel by default")
        public void shouldGetFirstChannel() {
            assertEquals(tvSet.getChannel(), 1);
        }

        @Test
        @DisplayName("can be turned off")
        public void shouldTornOff() {
            tvSet.turnOff();
            assertFalse(tvSet.isTurnedOn());
        }

        @Test
        @DisplayName("can select channel by number from 1 to 99")
        public void shouldSelectChannels() {
            assertTrue(tvSet.selectChannel(45));
            assertEquals(tvSet.getChannel(), 45);

            assertTrue(tvSet.selectChannel(1));
            assertEquals(tvSet.getChannel(), 1);

            assertTrue(tvSet.selectChannel(99));
            assertEquals(tvSet.getChannel(), 99);
        }

        @Test
        @DisplayName("can't select a channel by number outside of [1; 99]")
        public void shouldNotSelectChannels() {
            assertFalse(tvSet.selectChannel(0));
            assertEquals(tvSet.getChannel(), 1);

            assertFalse(tvSet.selectChannel(100));
            assertEquals(tvSet.getChannel(), 1);
        }

        @Nested
        @DisplayName("can select previous channel")
        class shouldSelectPreviousChannels {
            @Test
            @DisplayName("if the channels didn't switched should select 1 channel")
            public void shouldSelectFirstChannel() {
                assertTrue(tvSet.selectPreviousChannel());
                assertEquals(tvSet.getChannel(), 1);
            }

            @Test
            @DisplayName("if the channels were switched should select previous channel")
            public void shouldSelectPreviousChannel() {
                tvSet.selectChannel(10);
                tvSet.selectChannel(20);

                assertTrue(tvSet.selectPreviousChannel());
                assertEquals(tvSet.getChannel(), 10);

                assertTrue(tvSet.selectPreviousChannel());
                assertEquals(tvSet.getChannel(), 20);

                assertTrue(tvSet.selectPreviousChannel());
                assertEquals(tvSet.getChannel(), 10);
            }
        }

        @Nested
        @DisplayName("can set channel name")
        class shouldSetChannelsNames {
            @Test
            @DisplayName("can set channel name if channel number within [1; 99] and name not empty")
            public void shouldSetChannelName() {
                assertTrue(tvSet.setChannelName(1, "1 channel"));
                assertTrue(tvSet.setChannelName(99, "99 channel"));
            }

            @Test
            @DisplayName("can't set channel name if channel number outside of [1; 99]")
            public void shouldNotSetNameToChannelWithIncorrectNumber() {
                assertFalse(tvSet.setChannelName(0, "0 channel"));
                assertFalse(tvSet.setChannelName(100, "100 channel"));
            }

            @Test
            @DisplayName("can't set empty channel name")
            public void shouldNotSetEmptyChannelName() {
                assertFalse(tvSet.setChannelName(10, ""));
            }
        }

        @Test
        @DisplayName("should return 0 if given channel name is not specified")
        public void shouldGetNullChannel() {
            assertEquals(tvSet.getChannelByName("name"), 0);
        }

        @Test
        @DisplayName("should return an empty string if given number haven't name")
        public void shouldGetEmptyString() {
            assertEquals(tvSet.getChannelName(1), "");
        }

        @Nested
        @DisplayName("when channel name is set")
        class channelNameIsSet {
            @BeforeEach
            public void init() {
                tvSet.setChannelName(10, "name");
            }

            @Test
            @DisplayName("can get channel by name")
            public void shouldGetChannelByName() {
                assertEquals(tvSet.getChannelByName("name"), 10);
            }

            @Test
            @DisplayName("should get 0 channel by name if TV is turned off")
            public void shouldGetNullChanel() {
                tvSet.turnOff();
                assertEquals(tvSet.getChannelByName("name"), 0);
            }

            @Test
            @DisplayName("can get channel name by number")
            public void shouldGetChannelName() {
                assertEquals(tvSet.getChannelName(10), "name");
            }

            @Test
            @DisplayName("if TV is turned off, should return an empty string")
            public void shouldNotGetChannelName() {
                tvSet.turnOff();
                assertEquals(tvSet.getChannelName(10), "");
            }

            @Test
            @DisplayName("can select channel by name if given name is set")
            public void shouldSelectChannelByName() {
                assertTrue(tvSet.selectChannel("name"));
                assertEquals(tvSet.getChannel(), 10);
            }

            @Test
            @DisplayName("can't select channel by name if given name isn't set")
            public void shouldNotSelectChannelByName() {
                assertFalse(tvSet.selectChannel("unknown channel"));
            }

            @Test
            @DisplayName("can delete channel name if given name is set")
            public void shouldDeleteChannelName() {
                assertTrue(tvSet.deleteChannelName("name"));
                assertEquals(tvSet.getChannelByName("name"), 0);
            }

            @Test
            @DisplayName("can't delete channel name if given name isn't set")
            public void shouldNotDeleteChannelName() {
                assertFalse(tvSet.deleteChannelName("unknown channel"));
            }
        }

        @Nested
        @DisplayName("when tv turns on again")
        class TvTurnsOnAgain {
            @BeforeEach
            public void init() {
                tvSet.selectChannel(33);
                tvSet.turnOff();
                tvSet.turnOn();
            }

            @Test
            @DisplayName("restores the last selected channel after turning on the TV again")
            public void shouldRestoreLastSelectedChannel() {
                assertEquals(tvSet.getChannel(), 33);
            }
        }
    }
}