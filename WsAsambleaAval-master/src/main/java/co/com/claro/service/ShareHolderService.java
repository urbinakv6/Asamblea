package co.com.claro.service;

import co.com.claro.entity.AssemblyQuestions;
import co.com.claro.entity.ShareHolder;
import co.com.claro.repo.ShareHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShareHolderService {

    private final ShareHolderRepository shareHolderRepository;

    @Autowired
    public ShareHolderService(ShareHolderRepository shareHolderRepository){
        this.shareHolderRepository = shareHolderRepository;
    }

    public Optional<ShareHolder> getShareHolderById(String tipId, String numId, String numAccion){
        return this.shareHolderRepository.findByTipIdAndNumIdAndNumeroAccion(tipId, numId, numAccion);
    }

    public Optional<ShareHolder> getShareHolderByDoc(String tipId, String numId){
        return this.shareHolderRepository.findByTipIdAndNumId(tipId,numId);
    }

    public Optional<ShareHolder> getShareHolderByAction(String action){
        return this.shareHolderRepository.findByNumeroAccion(action);
    }
    public List<ShareHolder> getAllShareHolder(){
        return this.shareHolderRepository.findAll();
    }

    public ShareHolder createShareHolder(ShareHolder shareHolder){
        return this.shareHolderRepository.save(shareHolder);
    }

    public ShareHolder updateShareHolder(ShareHolder shareHolder){
        return this.shareHolderRepository.save(shareHolder);
    }

    public List<String> getActions(String tipId, String numID){
        return this.shareHolderRepository.findAllActionsByDocument(tipId, numID);
    }
    
    public void uploadShareHolders(List<ShareHolder> shareHolder) {
    	shareHolderRepository.saveAll(shareHolder);
    }
}
