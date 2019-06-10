package kr.co.rtst.autosar.ap4x.editor.page.section.applications;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;

import autosar40.swcomponent.communication.ReceiverComSpec;
import autosar40.system.transformer.EndToEndTransformationComSpecProps;
import autosar40.system.transformer.TransformerFactory;
import gautosar.ggenericstructure.ginfrastructure.GARObject;
import kr.co.rtst.autosar.ap4x.core.model.manager.SwComponentsModelManager;
import kr.co.rtst.autosar.ap4x.core.model.transaction.IAPTransactionalOperation;
import kr.co.rtst.autosar.ap4x.editor.page.AbstractAPEditorPage;

public class ReceiverComSpecTableSection extends AbstractTransformationComSpecPropsTableSection/*AbstractContentGUISection implements SelectionListener*/{
	
//	public static final String[] TABLE_COLUMN				 = {
//			"",
//			"Disable end to end check",
//			"Max delta counter",
//			"Max error state init",
//			"Max error state invalid",
//			"Max error state valid",
//			"Min ok state init",
//			"Min ok state invalid",
//			"Min ok state valid",
//			"Window size",
//	}; 
//	public static final int[] TABLE_COLUMN_WIDTH				 = {
//			0, 80 , 80 , 80 , 80, 80 , 80 , 80 , 80 , 80
//	}; 
//	public static final int[] TABLE_COLUMN_ALIGN			 = {
//			SWT.LEFT , SWT.CENTER , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , 
//			SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT , SWT.RIGHT
//	}; 
//	public static final boolean[] TABLE_COLUMN_LISTENER			 = {
//			false, false, false, false, false, 
//			false, false, false, false, false
//	}; 
//	public static final boolean[] TABLE_COLUMN_RESIZABLE			 = {
//			false, true, true, true, true,
//			true, true, true, true, true
//	}; 
//
//	private TableViewer tableTransformationComSpecProps;
//	private Button buttonAddTransformationComSpecProps;
//	private Button buttonRemoveTransformationComSpecProps;
	
	public ReceiverComSpecTableSection(AbstractAPEditorPage formPage, int style) {
		super(formPage, style, SwComponentsModelManager.TYPE_NAME_RECEIVER_COM_SPEC);
	}
	
	@Override
	protected void createSectionClientContentDetail(IManagedForm managedForm, SectionPart sectionPart, Composite parent) {
		
		sectionPart.getSection().setText("Transformation com spec props");
		sectionPart.getSection().setDescription("Transformation com spec props desc");
		
		createTable(parent);
//		tableTransformationComSpecProps = new TableViewer(APSectionUIFactory.createTable(
//				parent, ToolTipFactory.get(""), 
//				SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER | SWT.V_SCROLL| SWT.H_SCROLL,
//				CLIENT_CONTENT_COLUMN-2,
//				TABLE_COLUMN, 
//				TABLE_COLUMN_WIDTH, 
//				TABLE_COLUMN_ALIGN, 
//				TABLE_COLUMN_LISTENER, 
//				TABLE_COLUMN_RESIZABLE, 
//				null));
//		tableTransformationComSpecProps.setLabelProvider(new ModelLabelProvider());
//		tableTransformationComSpecProps.setContentProvider(new ModelContentProvider());
//		Composite compButton = new Composite(parent, SWT.NONE);
//		compButton.setLayout(APUIFactory.getGridLayoutDefault(1, false));
//		GridData gData = new GridData(GridData.FILL_VERTICAL);
//		gData.verticalAlignment = SWT.TOP;
//		compButton.setLayoutData(gData);
//		buttonAddTransformationComSpecProps = APSectionUIFactory.createButtonImage(compButton, ToolTipFactory.get(""), SWT.PUSH, APSectionUIFactory.BUTTON_IMAGE_PLUS, 1, this);
//		buttonRemoveTransformationComSpecProps = APSectionUIFactory.createButtonImage(compButton, ToolTipFactory.get(""), SWT.PUSH, APSectionUIFactory.BUTTON_IMAGE_MINUS, 1, this);
		
		hookListeners();
	}
	
	@Override
	public void initUIContents() {
		ReceiverComSpec input = getCastedInputDetail();
		if(input != null) {
			tableTransformationComSpecProps.setInput(input.getTransformationComSpecProps());
		}
	}

	private ReceiverComSpec getCastedInputDetail() {
		if(getInputDetail() instanceof ReceiverComSpec) {
			return (ReceiverComSpec)getInputDetail();
		}
		return null;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		if(e.getSource().equals(buttonAddTransformationComSpecProps)) {
			final ReceiverComSpec input = getCastedInputDetail();
			if(input != null) {
				doTransactionalOperation(new IAPTransactionalOperation() {
					
					@Override
					public GARObject doProcess(GARObject model) throws Exception {
						EndToEndTransformationComSpecProps item = TransformerFactory.eINSTANCE.createEndToEndTransformationComSpecProps();
						input.getTransformationComSpecProps().add(item);
						return model;
					}
				});
				
				tableTransformationComSpecProps.refresh();
				
				System.out.println("추가 후 개수:"+input.getTransformationComSpecProps().size());
			}
		} else if(e.getSource().equals(buttonRemoveTransformationComSpecProps)) {
			final ReceiverComSpec input = getCastedInputDetail();
			if(input != null) {
				if(!tableTransformationComSpecProps.getStructuredSelection().isEmpty()) {
					doTransactionalOperation(new IAPTransactionalOperation() {
						
						@Override
						public GARObject doProcess(GARObject model) throws Exception {
							input.getTransformationComSpecProps().removeAll(tableTransformationComSpecProps.getStructuredSelection().toList());
							return model;
						}
					});
					
					tableTransformationComSpecProps.refresh();
					
					System.out.println("삭제 후 개수:"+input.getTransformationComSpecProps().size());
				}
			}
		}		
	}

}
