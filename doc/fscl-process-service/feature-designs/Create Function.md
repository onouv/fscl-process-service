
| Client | Service |
| ---- | ---- |
| `POST fscl/v2/process/function [FunctionDto]` |  |
|  | FunctionController  <br><br>-> FunctionService.createFunction(...) <br>     -> f = new Function(...)<br>     -> sf = new ShadowFunction(f)<br>     -> FunctionRepo.save(f)<br>     -> ShadowFunctionRepo.save(sf)<br> |
|  |  |
