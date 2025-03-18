package se.sundsvall.citizen.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.violations.ConstraintViolationProblem;
import se.sundsvall.citizen.api.model.CitizenExtended;
import se.sundsvall.citizen.api.model.CitizenWithChangedAddress;
import se.sundsvall.citizen.api.model.ModelPostPerson;
import se.sundsvall.citizen.api.model.PersonGuidBatch;
import se.sundsvall.citizen.service.CitizenService;
import se.sundsvall.dept44.common.validators.annotation.ValidUuid;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Validated
@RequestMapping("/api/v2/citizen")
@Tag(name = "Citizen", description = "Show information about Citizens")
@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(oneOf = { Problem.class, ConstraintViolationProblem.class })))
@ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
@ApiResponse(responseCode = "503", description = "Server Error", content = @Content(mediaType = APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = Problem.class)))
public class CitizenResource {

    private final CitizenService citizenService;

    public CitizenResource(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping(path = "/{personId}", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Show information about specific citizen")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<CitizenExtended> getCitizenById(
            @Parameter(description = "ID for specific citizen")
            @ValidUuid @PathVariable final String personId,
            @Parameter(description = "If true, include search for classified persons")
            @RequestParam(defaultValue = "false") final boolean showClassified) {

        var citizen = citizenService.getCitizenById(UUID.fromString(personId), showClassified);
        return citizen != null ? ok(citizen) : ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/batch", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Show information about list of citizens")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<CitizenExtended>> getCitizensBatch(
            @Parameter(description = "If true, include search for classified persons")
            @RequestParam(defaultValue = "false") final boolean showClassified,
            @Valid @RequestBody List<UUID> personIds) {

        return ok(citizenService.getCitizensByIds(personIds, showClassified));
    }

    @GetMapping(path = "/changedaddress", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Show citizens that have moved since the given date")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<List<CitizenWithChangedAddress>> getCitizensWithChangedAddress(
            @Parameter(description = "From-date for move")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            final OffsetDateTime changedDateFrom) {

        var changedAddresses = citizenService.getCitizensWithChangedAddress(changedDateFrom);
        return !changedAddresses.isEmpty() ? ok(changedAddresses) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{personId}/personnumber", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get Personal identity number from personId")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<String> getPersonalNumberById(
            @Parameter(description = "personID, Guid, for specific citizen")
            @ValidUuid @PathVariable final String personId) {

        var personalNumber = citizenService.getPersonalNumberById(UUID.fromString(personId));
        return personalNumber != null ? ok(personalNumber) : ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/{personNumber}/guid", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get the personId from Personal identity number")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "404", description = "Not Found")
    public ResponseEntity<UUID> getPersonIdByPersonalNumber(
            @Parameter(description = "Personal identity number for specific citizen")
            @PathVariable final String personNumber) {

        return ok(citizenService.getPersonIdByPersonalNumber(personNumber));
    }

    @PostMapping(path = "/guid/batch", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get an array of personIds from Personal identity numbers")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<List<PersonGuidBatch>> getPersonIdsBatch(
            @Valid @RequestBody List<String> personalNumbers) {

        return ok(citizenService.getPersonIdsInBatch(personalNumbers));
    }

    @PostMapping(path = "/guid", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Create person")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "409", description = "Conflict")
    public ResponseEntity<UUID> createPerson(
            @Valid @RequestBody ModelPostPerson person) {

        return ok(citizenService.createPerson(person));
    }
}