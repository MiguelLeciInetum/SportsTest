package com.example.sportsproyect.mappers;
import com.example.sportsproyect.model.Team;
import com.example.sportsproyect.model.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = SportsMapperConfig.class)
public interface TeamMapper {
    List<TeamDto> fromTeamDtoTeams(List<Team> teams);
    List<Team> fromDtoTeamTeams(List<TeamDto> teams);
    @Mapping(target = "abbreviation", source = "abbreviation")
    TeamDto fromTeamDtoTeam(Team team);
}
