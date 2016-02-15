package io.github.pbremer.icecreammanager.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.github.pbremer.icecreammanager.entity.Chair;
import io.github.pbremer.icecreammanager.entity.Room;
import io.github.pbremer.icecreammanager.testconfig.TestEntityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TestEntityConfiguration.class)
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "spring.datasource.url = jdbc:h2:~/local;DB_CLOSE_ON_EXIT=FALSE" })
public class ChairRepositoryTest {
    
    @Autowired
    private ChairRepository chairRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    private Chair chair;
    private Chair stool;
    private Room room;
    
    @Before
    public void setUp() {
        
        room = new Room();
        room = new Room();
        room.setName("Classroom A1");
        room = roomRepository.save(room);
        
        chair = new Chair();
        chair.setType("Chair");
        chair.setNumberOfLegs(4);
        chair.setRoom(room);
        chair = chairRepository.save(chair);
        
        stool = new Chair();
        stool.setType("Stool");
        stool.setNumberOfLegs(3);
        stool.setRoom(room);
        stool = chairRepository.save(stool);
    }
    
    @Test
    public void findByNumberOfLegsTest() {
        Chair dbStool = chairRepository.findByNumberOfLegs(3).get(0);
        assertThat("Didn't return Stool entry", dbStool, equalTo(stool));
        
        Chair dbChair = chairRepository.findByNumberOfLegs(4).get(0);
        assertThat("Didn't return Chair entry", dbChair, equalTo(chair));
        
        assertThat("Rooms do not math",
                dbStool.getRoom().equals(dbChair.getRoom()));
    }
}
