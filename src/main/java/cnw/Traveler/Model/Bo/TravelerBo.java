package cnw.Traveler.Model.Bo;

import cnw.Traveler.Exception.*;
import cnw.Traveler.Model.Bean.Traveler;
import cnw.Traveler.Model.Dao.TralverTourDao;
import cnw.Traveler.Model.Dao.TravelerDao;
import cnw.Traveler.Model.dto.ChangePasswordDto;
import cnw.Traveler.Model.dto.RegisterTravelerDto;
import cnw.Traveler.Model.dto.UpdateTravelerProfileDto;

import java.util.Optional;

public class TravelerBo {
    TralverTourDao tralverTourDao = new TralverTourDao();
    TravelerDao travelerDao = new TravelerDao();
//    public void addTravelerToTour(Integer idTraveler,Integer idTour) throws Exception {
//        if(tralverTourDao.isTravelerInTour(idTraveler,idTour))
//        {
//            throw new TravelerAlreadyInTour(idTraveler,idTour);
//        }
//        else {
//            tralverTourDao.addTravelerToTour(idTraveler, idTour);
//        }
//    }
//    public void removeTravelerFromTour(Integer idTraveler,Integer idTour) throws Exception {
//        if (!tralverTourDao.isTravelerInTour(idTraveler,idTour))
//        {
//            throw new TravelerNotInTourYet(idTraveler,idTour);
//        }
//        else {
//            tralverTourDao.removeTravelerFromTour(idTraveler, idTour);
//        }
//    }
    public void updateTraveler(UpdateTravelerProfileDto traveler) {
        try {
            if (traveler.getAvatar() == null) {
                traveler.setFileName(getTravelerById(traveler.getId()).getAvatar());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        travelerDao.updateTraveler(traveler);
    }
    public Traveler getTravelerById(Integer id) throws Exception {
        Optional<Traveler> travelerOptional = travelerDao.getTravelerById(id);
        if(travelerOptional.isEmpty())
        {
            throw new TravelerIdNotExists(id);
        }
        else {
            return travelerOptional.get();
        }
    }
    public Traveler getTravelerByUsername(String username) throws TravelerNotFound {
        Optional<Traveler> travelerOptional = travelerDao.getTravelerByUsername(username);
        if(travelerOptional.isEmpty())
        {
            throw new TravelerNotFound(username);
        }
        else {
            return travelerOptional.get();
        }
    }
//    public List<Traveler> getAllTraveler() throws Exception {
//        return travelerDao.getAllTraveler();
//    }
//    public List<Traveler> getAllTravelerInTour(Integer idTour) throws Exception {
//        return travelerDao.getAllTravelerInTour(idTour);
//    }
    public void registerTraveler(RegisterTravelerDto registerTravelerDto) throws TravelerUsernameAlreadyExists, PasswordNotStrongEnough {
        Optional<Traveler> travelerOptional = travelerDao.getTravelerByUsername(registerTravelerDto.getUsername());
        if(isPasswordStrongEnough(registerTravelerDto.getPassword()))
        {
            throw new PasswordNotStrongEnough(registerTravelerDto.getPassword());
        }
        if(travelerOptional.isPresent()){
            throw new TravelerUsernameAlreadyExists(registerTravelerDto.getUsername());
        }
        else {
            travelerDao.registerTraveler(registerTravelerDto);
        }
    }

    public void changePassword(ChangePasswordDto changePasswordDto) throws PasswordNotStrongEnough, TravelerCredentialNotCorrect {
            Optional<Traveler> travelerOptional = travelerDao.getTravelerById(changePasswordDto.getId())
                    .filter(traveler -> traveler.getPassword().equals(changePasswordDto.getOldPassword()));
            if(travelerOptional.isEmpty())
            {
                throw new TravelerCredentialNotCorrect("credential not correct \n old pass: " + changePasswordDto.getOldPassword()
                + "\n new pass: " + changePasswordDto.getNewPassword());
            }

            if (isPasswordStrongEnough(changePasswordDto.getNewPassword()))
            {
                throw new PasswordNotStrongEnough(changePasswordDto.getNewPassword());
            }
            travelerDao.changePassword(changePasswordDto);
    }
    public boolean isPasswordStrongEnough(String password)
    {
        // check if password has at lease 8 character include number, uppercase and lowercase enough using regex
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return !password.matches(regex);
    }
}
