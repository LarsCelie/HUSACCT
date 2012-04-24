package husacct.graphics.task;

import org.apache.log4j.Logger;

import husacct.ServiceProvider;
import husacct.analyse.AnalyseServiceStub;
import husacct.analyse.IAnalyseService;
import husacct.common.dto.AbstractDTO;
import husacct.common.dto.AnalysedModuleDTO;
import husacct.control.ControlServiceImpl;
import husacct.graphics.presentation.decorators.DTODecorator;
import husacct.graphics.presentation.figures.BaseFigure;

public class AnalysedController extends BaseController {

	private IAnalyseService analyseService;
	private Logger logger = Logger.getLogger(AnalysedController.class);

	public AnalysedController() {
		super();
		analyseService = ServiceProvider.getInstance().getAnalyseService();
	}

	public void drawArchitecture(DrawingDetail detail) {
		AbstractDTO[] modules = analyseService.getRootModules();
		drawModules(modules);
	}

	private void drawModules(AbstractDTO[] modules) {
		AnalysedModuleDTO[] castedModules = (AnalysedModuleDTO[]) modules;
		this.clearDrawing();
		for (AnalysedModuleDTO dto : castedModules) {
			BaseFigure packageFigure = figureFactory.createFigure(dto);
			drawing.add(packageFigure);
		}
	}
	
	private void getAndDrawModulesIn(String parentName){
		AnalysedModuleDTO[] children = analyseService.getChildModulesInModule(parentName);
		if(children.length>0){
			drawModules(children);
		}else{
			logger.debug("Tried to draw modules for "+parentName+", but it has no children.");
		}
	}

	@Override
	public void moduleZoom(BaseFigure zoomedModuleFigure) {
		AbstractDTO dto = ((DTODecorator) zoomedModuleFigure).getDTO();
		switch(dto.getClass().getSimpleName()){
			case "AnalysedModuleDTO":
				AnalysedModuleDTO newdto = ((AnalysedModuleDTO)dto);
				getAndDrawModulesIn(newdto.uniqueName);
		}
	}

	@Override
	public void zoomOut(AbstractDTO childDTO) {
		switch(childDTO.getClass().getSimpleName()){
			case "AnalysedModuleDTO":
				AnalysedModuleDTO newdto = ((AnalysedModuleDTO)childDTO);
				AnalysedModuleDTO parentDTO = analyseService.getParentModuleForModule(newdto.uniqueName);
				if(parentDTO!=null){
					getAndDrawModulesIn(parentDTO.uniqueName);
				}else{
					logger.debug("Tried to zoom out from "+newdto.name+", but it has no parent.");
				}
		}
	}
}
