package com.rfb.web.rest;

import com.rfb.RfbloyaltyApp;

import com.rfb.domain.RfbEvAtt;
import com.rfb.repository.RfbEvAttRepository;
import com.rfb.service.RfbEvAttService;
import com.rfb.service.dto.RfbEvAttDTO;
import com.rfb.service.mapper.RfbEvAttMapper;
import com.rfb.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.rfb.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RfbEvAttResource REST controller.
 *
 * @see RfbEvAttResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RfbloyaltyApp.class)
public class RfbEvAttResourceIntTest {

    private static final LocalDate DEFAULT_ATTENDANCE_DAY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ATTENDANCE_DAY = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RfbEvAttRepository rfbEvAttRepository;


    @Autowired
    private RfbEvAttMapper rfbEvAttMapper;
    

    @Autowired
    private RfbEvAttService rfbEvAttService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRfbEvAttMockMvc;

    private RfbEvAtt rfbEvAtt;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RfbEvAttResource rfbEvAttResource = new RfbEvAttResource(rfbEvAttService);
        this.restRfbEvAttMockMvc = MockMvcBuilders.standaloneSetup(rfbEvAttResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RfbEvAtt createEntity(EntityManager em) {
        RfbEvAtt rfbEvAtt = new RfbEvAtt()
            .attendanceDay(DEFAULT_ATTENDANCE_DAY);
        return rfbEvAtt;
    }

    @Before
    public void initTest() {
        rfbEvAtt = createEntity(em);
    }

    @Test
    @Transactional
    public void createRfbEvAtt() throws Exception {
        int databaseSizeBeforeCreate = rfbEvAttRepository.findAll().size();

        // Create the RfbEvAtt
        RfbEvAttDTO rfbEvAttDTO = rfbEvAttMapper.toDto(rfbEvAtt);
        restRfbEvAttMockMvc.perform(post("/api/rfb-ev-atts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rfbEvAttDTO)))
            .andExpect(status().isCreated());

        // Validate the RfbEvAtt in the database
        List<RfbEvAtt> rfbEvAttList = rfbEvAttRepository.findAll();
        assertThat(rfbEvAttList).hasSize(databaseSizeBeforeCreate + 1);
        RfbEvAtt testRfbEvAtt = rfbEvAttList.get(rfbEvAttList.size() - 1);
        assertThat(testRfbEvAtt.getAttendanceDay()).isEqualTo(DEFAULT_ATTENDANCE_DAY);
    }

    @Test
    @Transactional
    public void createRfbEvAttWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rfbEvAttRepository.findAll().size();

        // Create the RfbEvAtt with an existing ID
        rfbEvAtt.setId(1L);
        RfbEvAttDTO rfbEvAttDTO = rfbEvAttMapper.toDto(rfbEvAtt);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRfbEvAttMockMvc.perform(post("/api/rfb-ev-atts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rfbEvAttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RfbEvAtt in the database
        List<RfbEvAtt> rfbEvAttList = rfbEvAttRepository.findAll();
        assertThat(rfbEvAttList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRfbEvAtts() throws Exception {
        // Initialize the database
        rfbEvAttRepository.saveAndFlush(rfbEvAtt);

        // Get all the rfbEvAttList
        restRfbEvAttMockMvc.perform(get("/api/rfb-ev-atts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rfbEvAtt.getId().intValue())))
            .andExpect(jsonPath("$.[*].attendanceDay").value(hasItem(DEFAULT_ATTENDANCE_DAY.toString())));
    }
    

    @Test
    @Transactional
    public void getRfbEvAtt() throws Exception {
        // Initialize the database
        rfbEvAttRepository.saveAndFlush(rfbEvAtt);

        // Get the rfbEvAtt
        restRfbEvAttMockMvc.perform(get("/api/rfb-ev-atts/{id}", rfbEvAtt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rfbEvAtt.getId().intValue()))
            .andExpect(jsonPath("$.attendanceDay").value(DEFAULT_ATTENDANCE_DAY.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingRfbEvAtt() throws Exception {
        // Get the rfbEvAtt
        restRfbEvAttMockMvc.perform(get("/api/rfb-ev-atts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRfbEvAtt() throws Exception {
        // Initialize the database
        rfbEvAttRepository.saveAndFlush(rfbEvAtt);

        int databaseSizeBeforeUpdate = rfbEvAttRepository.findAll().size();

        // Update the rfbEvAtt
        RfbEvAtt updatedRfbEvAtt = rfbEvAttRepository.findById(rfbEvAtt.getId()).get();
        // Disconnect from session so that the updates on updatedRfbEvAtt are not directly saved in db
        em.detach(updatedRfbEvAtt);
        updatedRfbEvAtt
            .attendanceDay(UPDATED_ATTENDANCE_DAY);
        RfbEvAttDTO rfbEvAttDTO = rfbEvAttMapper.toDto(updatedRfbEvAtt);

        restRfbEvAttMockMvc.perform(put("/api/rfb-ev-atts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rfbEvAttDTO)))
            .andExpect(status().isOk());

        // Validate the RfbEvAtt in the database
        List<RfbEvAtt> rfbEvAttList = rfbEvAttRepository.findAll();
        assertThat(rfbEvAttList).hasSize(databaseSizeBeforeUpdate);
        RfbEvAtt testRfbEvAtt = rfbEvAttList.get(rfbEvAttList.size() - 1);
        assertThat(testRfbEvAtt.getAttendanceDay()).isEqualTo(UPDATED_ATTENDANCE_DAY);
    }

    @Test
    @Transactional
    public void updateNonExistingRfbEvAtt() throws Exception {
        int databaseSizeBeforeUpdate = rfbEvAttRepository.findAll().size();

        // Create the RfbEvAtt
        RfbEvAttDTO rfbEvAttDTO = rfbEvAttMapper.toDto(rfbEvAtt);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRfbEvAttMockMvc.perform(put("/api/rfb-ev-atts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rfbEvAttDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RfbEvAtt in the database
        List<RfbEvAtt> rfbEvAttList = rfbEvAttRepository.findAll();
        assertThat(rfbEvAttList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRfbEvAtt() throws Exception {
        // Initialize the database
        rfbEvAttRepository.saveAndFlush(rfbEvAtt);

        int databaseSizeBeforeDelete = rfbEvAttRepository.findAll().size();

        // Get the rfbEvAtt
        restRfbEvAttMockMvc.perform(delete("/api/rfb-ev-atts/{id}", rfbEvAtt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RfbEvAtt> rfbEvAttList = rfbEvAttRepository.findAll();
        assertThat(rfbEvAttList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RfbEvAtt.class);
        RfbEvAtt rfbEvAtt1 = new RfbEvAtt();
        rfbEvAtt1.setId(1L);
        RfbEvAtt rfbEvAtt2 = new RfbEvAtt();
        rfbEvAtt2.setId(rfbEvAtt1.getId());
        assertThat(rfbEvAtt1).isEqualTo(rfbEvAtt2);
        rfbEvAtt2.setId(2L);
        assertThat(rfbEvAtt1).isNotEqualTo(rfbEvAtt2);
        rfbEvAtt1.setId(null);
        assertThat(rfbEvAtt1).isNotEqualTo(rfbEvAtt2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RfbEvAttDTO.class);
        RfbEvAttDTO rfbEvAttDTO1 = new RfbEvAttDTO();
        rfbEvAttDTO1.setId(1L);
        RfbEvAttDTO rfbEvAttDTO2 = new RfbEvAttDTO();
        assertThat(rfbEvAttDTO1).isNotEqualTo(rfbEvAttDTO2);
        rfbEvAttDTO2.setId(rfbEvAttDTO1.getId());
        assertThat(rfbEvAttDTO1).isEqualTo(rfbEvAttDTO2);
        rfbEvAttDTO2.setId(2L);
        assertThat(rfbEvAttDTO1).isNotEqualTo(rfbEvAttDTO2);
        rfbEvAttDTO1.setId(null);
        assertThat(rfbEvAttDTO1).isNotEqualTo(rfbEvAttDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rfbEvAttMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rfbEvAttMapper.fromId(null)).isNull();
    }
}
